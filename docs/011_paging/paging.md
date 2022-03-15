# Paging library

## What
- The Paging library helps you load and display pages of data from a larger dataset from local storage or over network.

## Why/Pros
- In-memory caching for your paged data.
- Built-in request deduplication.
- Support RecyclerView and Compose UI
- First-class support for Kotlin coroutines and Flow, as well as LiveData and RxJava.
- Built-in support for error handling, including refresh and retry capabilities.

## How

!['paging3-library-architecture'](paging3-library-architecture.svg)

### The library's components operate in three layers of your app:

In repository layer
- PagingSource defines a source of data and how to retrieve data from that source.
- A RemoteMediator to work with a network data source with a local database cache.

In ViewModel layer
- The Pager: Construct PagingData object from PagingSource with PagingConfig.

- PagingData: is a container for a snapshot of paginated data. It queries a PagingSource object and stores the result.

In UI layer

- XML: PagingDataAdapter/AsyncPagingDataDiffer, a RecyclerView adapter that handles paginated data.

- Compose: androidx.paging:paging-compose  Flow<PagingData<DataType>>.collectAsLazyPagingItems

