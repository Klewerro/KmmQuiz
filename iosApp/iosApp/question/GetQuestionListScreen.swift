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
    @ObservedObject var viewModel: IOSGetQuestionListViewModel
    
    init(getQuestionListUseCase: GetQuestionListUseCase) {
        self.getQuestionListUseCase = getQuestionListUseCase // Todo: Remove?
        self.viewModel = IOSGetQuestionListViewModel(getQuestionListUseCase: getQuestionListUseCase)
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
            }
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
