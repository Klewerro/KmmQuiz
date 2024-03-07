//
//  ErrorMessageSheetContent.swift
//  iosApp
//
//  Created by Szymon M on 03/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ErrorMessageSheetContent: View {
    var error: GetQuestionListError
    
    var body: some View {
        ZStack {
            let message = switch error {
                case .serviceUnavailable:
                    SharedStrings().get(id: SharedRes.strings().error_service_unavailable, args: [])
                    
                case .clientError:
                    SharedStrings().get(id: SharedRes.strings().client_error, args: [])
                    
                case .serializationError:
                    SharedStrings().get(id: SharedRes.strings().client_error, args: [])
                    
                case .tooManyRequestsError:
                    SharedStrings().get(id: SharedRes.strings().too_many_requests_error, args: [])
                    
                case .unknownError:
                    SharedStrings().get(id: SharedRes.strings().unknown_error, args: [])
                    
                case .amountIs0:
                    SharedStrings().get(id: SharedRes.strings().amount_is_0_error, args: [])
                    
                case .serverError:
                    SharedStrings().get(id: SharedRes.strings().server_error, args: [])
                    
                case .questionAlreadySaved:
                    SharedStrings().get(id: SharedRes.strings().question_already_saved_error, args: [])
                    
                default: SharedStrings().get(id: SharedRes.strings().unknown_error, args: [])
            }
            
            Color(SharedRes.colors().primary.getUiColor())
            Text(message)
                .foregroundStyle(Color(SharedRes.colors().onPrimary.getUiColor()))
        }
    }
}

#Preview {
    ErrorMessageSheetContent(error: .amountIs0)
}
