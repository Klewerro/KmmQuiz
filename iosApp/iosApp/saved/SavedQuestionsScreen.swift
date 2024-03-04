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
    private var savedQuestionsUseCase: SavedQuestionsUseCase
    private var localDbDataSource: LocalDbDataSource
    @ObservedObject var viewModel: IOSSavedQuestionsViewModel
    
    init(savedQuestionsUseCase: SavedQuestionsUseCase, localDbDataSource: LocalDbDataSource) {
        self.savedQuestionsUseCase = savedQuestionsUseCase
        self.localDbDataSource = localDbDataSource
        self.viewModel = IOSSavedQuestionsViewModel(
            savedQuestionsUseCase: savedQuestionsUseCase,
            localDbDataSource: localDbDataSource
        )
    }
    
    @State private var isAnyQuestionSelected = false
    
    var body: some View {
        ZStack {
            ScrollView {
                // Add header here
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
            
            // Add button here
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
        // add snackbar here
        .padding(16)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
