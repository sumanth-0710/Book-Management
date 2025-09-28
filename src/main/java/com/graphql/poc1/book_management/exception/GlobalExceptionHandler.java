package com.graphql.poc1.book_management.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GlobalExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof BookNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.DataFetchingException)
                    .extensions(Collections.singletonMap("code", "BOOK_NOT_FOUND"))
                    .build();
        }

        // fallback for other exceptions
        return GraphqlErrorBuilder.newError(env)
                .message("Internal server error")
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
}
