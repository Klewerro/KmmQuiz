//
//  QuizScreen.swift
//  iosApp
//
//  Created by Szymon M on 04/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct QuizScreen: View {
    
    @ObservedObject var viewModel: IOSQuizViewModel
    
    init(viewModel: IOSQuizViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        ScrollView {
            ForEach(viewModel.state.quizList, id: \.self.id) { quiz in
                NavigationLink(
                    destination: QuizDetailsScreen(quiz: quiz)
                ) {
                    VStack {
                        Text(quiz.title)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .foregroundStyle(Color(SharedRes.colors().onPrimaryContainer.getUiColor()))
                        HStack {
                            Text(SharedStrings().get(id: SharedRes.strings().questions_size, args: [quiz.questions.count]))
                                .foregroundStyle(Color(SharedRes.colors().onPrimaryContainer.getUiColor()))
                            Spacer()
                            Text(SharedStrings().get(id: SharedRes.strings().save_time, args: [quiz.getFormattedTime()]))
                                .foregroundStyle(Color(SharedRes.colors().onPrimaryContainer.getUiColor()))
                        }
                        .frame(maxWidth: .infinity, alignment: .leading)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(8)
                    .background(Color(SharedRes.colors().primaryContainer.getUiColor()))
                    .clipShape(RoundedRectangle(cornerRadius: 8))
                    .padding(8)
                    .cornerRadius(8.0)
                }
                .navigationTitle("Quiz")
            }
        }
    }
}
