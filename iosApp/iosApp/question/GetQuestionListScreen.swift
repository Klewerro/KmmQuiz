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
                ProgressButton(
                    isLoading: viewModel.state.isFetchingData,
                    labelText: SharedStrings().get(id: SharedRes.strings().get_questions, args: []),
                    progressText: SharedStrings().get(id: SharedRes.strings().getting_questions, args: []),
                    onClick: {
                        viewModel.onEvent(.GetNewQuestionList())
                    })
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
