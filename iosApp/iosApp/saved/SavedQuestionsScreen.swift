//
//  SavedQuestionsScreen.swift
//  iosApp
//
//  Created by Szymon M on 03/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SavedQuestionsScreen: View {
    @ObservedObject var viewModel: IOSSavedQuestionsViewModel
    
    init(viewModel: IOSSavedQuestionsViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        ZStack {
            ScrollView {
                if viewModel.state.isAnyQuestionSelected() {
                    SaveQuizHeader(
                        quizTitleText: Binding(
                            get: { viewModel.state.newQuizName },
                            set: { text in
                                viewModel.onEvent(.ChangeQuizNameText(nameText: text))
                            }),
                        onSaveButtonClick: {
                            viewModel.onEvent(.SaveQuiz())
                        }
                    )
                }
                    
                ForEach(viewModel.state.savedQuestions, id: \.self.question.text) {savedQuestion in
                    SelectableSavedQuestionListItem(
                        selectableSavedQuestion: savedQuestion,
                        onDeleteClick: {
                            viewModel.onEvent(.DeleteQuestion(question: savedQuestion.question))
                        },
                        onSelectedChanged: { isSelected in
                            viewModel.onEvent(
                                .QuestionSelectionChanged(selectableQuestion: savedQuestion, isSelected: isSelected)
                            )
                        }
                    )
                }
            }
            
            if viewModel.state.isAnyQuestionSelected() {
                VStack {
                    Spacer()
                    HStack {
                        Spacer()
                        Text(String(viewModel.state.selectedQuestionsCount()))
                            .frame(width: 40, height: 40)
                            .font(.system(size: 18))
                            .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
                            .background(
                                Circle()
                                    .fill(Color(SharedRes.colors().primary.getUiColor()))
                            )
                    }
                }
            }
            
        }
        .sheet(
            isPresented: Binding(
                get: { viewModel.state.saveQuizResult != nil },
                set: {_ in
                    viewModel.onEvent(.OnQuizCreateMessageSeen())
                }
            )
        ) {
            ZStack {
                if let saveQuizResultValue = viewModel.state.saveQuizResult {
                    let message = if saveQuizResultValue.boolValue {
                        SharedStrings().get(id: SharedRes.strings().quiz_created_successfully, args: [])
                    } else {
                        SharedStrings().get(id: SharedRes.strings().quiz_creation_error, args: [])
                    }
                    Color(SharedRes.colors().primary.getUiColor())
                    Text(message)
                        .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
                }
            }
            .task {
                do {
                    try await Task.sleep(nanoseconds: 3_000_000_000)
                    viewModel.onEvent(.OnQuizCreateMessageSeen())
                } catch {
                    viewModel.onEvent(.OnQuizCreateMessageSeen())
                }
            }
            .ignoresSafeArea()
            .presentationDetents([.fraction(0.1)])
        
        }
        .padding(16)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
