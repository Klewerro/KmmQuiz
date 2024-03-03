//
//  QuestionListItem.swift
//  iosApp
//
//  Created by Szymon M on 02/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct QuestionListItem: View {
    var question: Question // Todo: TEST let!
    var onSaveButtonClick: () -> Void
    @State private var selected: String = ""
    
    var body: some View {
        VStack {
            HStack {
                Text(question.text)
                    .lineLimit(10)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(8)
                Button(action: onSaveButtonClick) {
                    Image(systemName: "plus")
                        .font(.system(size: 25))
                        .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
                        .frame(width: 40, height: 40)
                }
                .background(
                    Circle()
                        .fill(Color(SharedRes.colors().primary.getUiColor()))
                )
                
            }
            List {
                Picker("Answers", selection: $selected) {
                    ForEach(question.allAnswers, id: \.self) { answerText in
                        Text(answerText)
                    }
                }
                .pickerStyle(.inline)
                .listRowBackground(Color(SharedRes.colors().secondaryContainer.getUiColor()))
            }
            .scrollContentBackground(.hidden)
             .background(Color(SharedRes.colors().secondaryContainer.getUiColor()))
        }
        .frame(maxWidth: .infinity, minHeight: CGFloat(question.type == .multiple ? 340 : 220), alignment: .leading)
        .padding(8)
        .background(Color(SharedRes.colors().secondaryContainer.getUiColor()))
        .cornerRadius(15.0)
        
    }
}

#Preview {
    QuestionListItem(
        question: Question(
            category: "General knowledge",
            difficulty: .medium,
            correctAnswer: "Correct answer",
            incorrectAnswers: [
                "Incorrect 1",
                "Incorrect 2",
                "Incorrect 3",
            ],
            type: .multiple,
            text: "Question text description.",
            allAnswers: [
                "Incorrect 1",
                "Incorrect 2",
                "Correct answer",
                "Incorrect 3",
            ]
        ), 
        onSaveButtonClick: {}
    )
}
