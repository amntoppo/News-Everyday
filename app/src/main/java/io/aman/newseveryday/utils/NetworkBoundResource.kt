package io.aman.newseveryday.utils

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType>NetworkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchRequest: suspend(RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = {true}
) = flow {
    val data = query().first()

    val flow = if(shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchRequest(fetch())
            query().map { Resource.Success(it)}
        } catch (throwable: Throwable)  {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}

