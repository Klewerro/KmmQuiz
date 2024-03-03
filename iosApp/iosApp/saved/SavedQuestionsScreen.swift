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
    
    var body: some View {
        ScrollView {
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
        .padding(16)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
