//
//  IOSSavedQuestionsViewModel.swift
//  iosApp
//
//  Created by Szymon M on 03/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class IOSSavedQuestionsViewModel: ObservableObject {
    private var savedQuestionsUseCase: SavedQuestionsUseCase
    private var localDbDataSource: LocalDbDataSource
    
    private var handle: DisposableHandle?
    private let viewModel: SavedQuestionsViewModel
    
    @Published var state = SavedQuestionsState(
        savedQuestions: [],
        newQuizName: "",
        saveQuizResult: nil
    )
    
    init(savedQuestionsUseCase: SavedQuestionsUseCase, localDbDataSource: LocalDbDataSource) {
        self.savedQuestionsUseCase = savedQuestionsUseCase
        self.localDbDataSource = localDbDataSource
        self.viewModel = SavedQuestionsViewModel(
            savedQuestionsUseCase: savedQuestionsUseCase,
            localDbDataSource: localDbDataSource,
            coroutineScope: nil
        )
    }
    
    func onEvent(_ event: SavedQuestionsEvent) {
        self.viewModel.onEvent(event: event)
    }
    
    func startObserving() {
        handle = viewModel.state.subscribe(onCollect: { state in
            if let state = state {
                self.state = state
            }
        })
    }
    
    func dispose() {
        handle?.dispose()
    }
}
