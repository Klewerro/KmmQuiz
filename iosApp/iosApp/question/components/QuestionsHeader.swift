//
//  QuestionsHeader.swift
//  iosApp
//
//  Created by Szymon M on 02/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct QuestionsHeader: View {
    @Binding var amountOfQuestionsText: String
    var questionCategories: [QuestionCategory]
    var currentQuestionCategory: QuestionCategory
    var onCategoryClick: (QuestionCategory) -> Void
    var onGetQuestionsClick: () -> Void
    var isFetchingData: Bool
    
    var body: some View {
        VStack(alignment: .center, spacing: 20.0) {
            HStack {
                TextField(
                    SharedStrings().get(id: SharedRes.strings().number_of_questions, args: []),
                    text: $amountOfQuestionsText
                )
                .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
                .accentColor(Color(SharedRes.colors().onPrimary.getUiColor()))
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .padding()
                .background(Color(SharedRes.colors().primary.getUiColor()))
                .padding(.bottom, 4)
                .cornerRadius(4)
                .padding(.bottom, -4)
                .shadow(radius: 4)
                .padding(8)
                
                Menu {
                    VStack {
                        ForEach(questionCategories, id: \.self.id) { category in
                            Button(action: {
                                onCategoryClick(category)
                            }) {
                                Text(category.formattedName)
                            }
                        }
                    }
                    
                } label: {
                    Text(currentQuestionCategory.formattedName)
                        .foregroundStyle(Color(SharedRes.colors().onPrimaryContainer.getUiColor()))
                        .lineLimit(/*@START_MENU_TOKEN@*/2/*@END_MENU_TOKEN@*/)
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .background(Color(SharedRes.colors().primaryContainer.getUiColor()))
                .padding(.bottom, 4)
                .cornerRadius(4)
                .padding(.bottom, -4)
                .shadow(radius: 4)
                .padding(8)
            }
            .frame(width: .infinity, height: 80)
            
            ProgressButton(
                isLoading: isFetchingData,
                labelText: SharedStrings().get(id: SharedRes.strings().get_questions, args: []),
                progressText: SharedStrings().get(id: SharedRes.strings().getting_questions, args: []),
                onClick: onGetQuestionsClick
            )
        }
    }
}

#Preview {
    QuestionsHeader(
        amountOfQuestionsText:  Binding(
            get: { "3" },
            set: {_ in }
        ),
        questionCategories: QuestionCategory.entries,
        currentQuestionCategory: .generalKnowledge,
        onCategoryClick: {_ in },
        onGetQuestionsClick: {},
        isFetchingData: false
    )
}
