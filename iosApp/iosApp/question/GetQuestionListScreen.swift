//
//  GetQuestionListScreen.swift
//  iosApp
//
//  Created by Szymon M on 01/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GetQuestionListScreen: View {
    
    private var getQuestionListUseCase: GetQuestionListUseCase // Todo: Remove?
    private var keyValueStorage: KeyValueStorage
    private var localDbDataSource: LocalDbDataSource
    @ObservedObject var viewModel: IOSGetQuestionListViewModel
    
    init(getQuestionListUseCase: GetQuestionListUseCase, keyValueStorage: KeyValueStorage, localDbDataSource: LocalDbDataSource) {
        self.getQuestionListUseCase = getQuestionListUseCase // Todo: Remove?
        self.keyValueStorage = keyValueStorage
        self.localDbDataSource = localDbDataSource
        self.viewModel = IOSGetQuestionListViewModel(
            getQuestionListUseCase: getQuestionListUseCase,
            keyValueStorage: keyValueStorage,
            localDbDataSource: localDbDataSource
        )
    }
    
    var body: some View {
        ScrollView {
            VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/, spacing: 20.0) {
                QuestionsHeader(
                    amountOfQuestionsText: Binding(
                        get: { String(viewModel.state.amountOfQuestions) },
                        set: { value in
                            viewModel.onEvent(.ChangeAmount(amountText: value))
                        }),
                    questionCategories: QuestionCategory.entries,
                    currentQuestionCategory: viewModel.state.questionCategory,
                    onCategoryClick: { questionCategory in
                        viewModel.onEvent(.ChangeQuestionCategory(questionCategory: questionCategory))
                    },
                    onGetQuestionsClick: {
                        viewModel.onEvent(.GetNewQuestionList())
                    },
                    isFetchingData: viewModel.state.isFetchingData
                )
                ForEach(viewModel.state.questions, id: \.self.text) {question in
                    QuestionListItem(
                        question: question,
                        onSaveButtonClick: {
                            viewModel.onEvent(.SaveQuestion(question: question))
                        }
                    )
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
            .padding(16)
        }
        .sheet(
            isPresented: Binding(
                get: { viewModel.state.error != nil },
                set: {_ in
                    viewModel.onEvent(.OnErrorSeen())
                }
            )
        ) {
            ZStack {
                ErrorMessageSheetContent(error: viewModel.state.error ?? .unknownError)
            }
            .task {
                do {
                    try await Task.sleep(nanoseconds: 3_000_000_000)
                    viewModel.onEvent(.OnErrorSeen())
                } catch {
                    viewModel.onEvent(.OnErrorSeen())
                }
            }
            .ignoresSafeArea()
            .presentationDetents([.fraction(0.1)])
        
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

//#Preview {
//    GetQuestionListScreen()
//}
