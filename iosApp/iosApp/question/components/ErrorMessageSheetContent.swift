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
            case GetQuestionListError.serviceUnavailable:
                SharedStrings().get(id: SharedRes.strings().error_service_unavailable, args: [])
                
            case GetQuestionListError.clientError:
                SharedStrings().get(id: SharedRes.strings().client_error, args: [])
                
            case GetQuestionListError.serializationError:
                SharedStrings().get(id: SharedRes.strings().client_error, args: [])
                
            case GetQuestionListError.tooManyRequestsError:
                SharedStrings().get(id: SharedRes.strings().too_many_requests_error, args: [])
                
            case GetQuestionListError.unknownError:
                SharedStrings().get(id: SharedRes.strings().unknown_error, args: [])
                
            case GetQuestionListError.amountIs0:
                SharedStrings().get(id: SharedRes.strings().amount_is_0_error, args: [])
                
            case GetQuestionListError.serverError:
                SharedStrings().get(id: SharedRes.strings().server_error, args: [])
                
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
