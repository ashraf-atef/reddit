package com.example.reddit.posts.presentation

import com.example.common.common.presentation.Fail
import com.example.common.common.presentation.Loading
import com.example.common.common.presentation.Success
import com.example.common.common.presentation.Uninitialized
import com.example.common.common.presentation.schedulers.SchedulersProvider
import com.example.reddit.TestingSchedulersProvider
import com.example.reddit.favorite.domain.AddingFavourite
import com.example.reddit.posts.data.model.PostsResponse
import com.example.reddit.posts.data.model.PostsResponseData
import com.example.reddit.posts.domain.FetchingPostsUseCase
import com.example.reddit.test
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class PostsViewModelTest {
    @MockK
    private lateinit var fetchingPostsUseCase: FetchingPostsUseCase

    @MockK
    private lateinit var addingFavourite: AddingFavourite
    private val schedulersProvider: SchedulersProvider = TestingSchedulersProvider()
    lateinit var postsViewModel: PostsViewModel

    @Before
    fun init() {
        MockKAnnotations.init(this)
        mockFetingPosts()
        postsViewModel = PostsViewModel(fetchingPostsUseCase, addingFavourite, schedulersProvider)
    }

    @Test
    fun `fetch posts WHEN posts use-case returns Success EXPECTED publish posts to the state`() {
        postsViewModel.stateFlow.test {
            postsViewModel.fetchPosts()

            assertTrue(it[1].posts is Loading)
            assertEquals(
                Success(
                        PostsResponseData(
                            "",
                            listOf()
                        )
                    ),
                it[2].posts
            )
        }
    }

    @Test
    fun `fetch posts WHEN posts use-case returns error EXPECTED publish error to the state`() {
        every { fetchingPostsUseCase(any(), any(), any()) } returns Single.error(
            UnknownHostException()
        )

        postsViewModel.stateFlow.test {
            postsViewModel.fetchPosts()

            assertTrue(it[1].posts is Loading)
            assertTrue(it[2].posts is Fail)
            assertTrue((it[2].posts as Fail).error is UnknownHostException)
        }
    }

    @Test
    fun `search posts WHEN posts use-case returns Success EXPECTED publish posts to the state`() {
        postsViewModel.stateFlow.test {
            postsViewModel.searchPosts("")

            assertTrue(it[1].posts is Uninitialized)
            assertTrue(it[2].posts is Loading)
            assertEquals(
                Success(
                    PostsResponseData(
                        "",
                        listOf()
                    )
                ),
                it[3].posts
            )
        }
    }

    @Test
    fun `search posts WHEN posts use-case returns error EXPECTED publish error to the state`() {
        every { fetchingPostsUseCase(any(), any(), any()) } returns Single.error(
            UnknownHostException()
        )

        postsViewModel.stateFlow.test {
            postsViewModel.searchPosts("")

            assertTrue(it[1].posts is Uninitialized)
            assertTrue(it[2].posts is Loading)
            assertTrue(it[3].posts is Fail)
            assertTrue((it[3].posts as Fail).error is UnknownHostException)
        }
    }

    private fun mockFetingPosts() {
        every { fetchingPostsUseCase(any(), any(), any()) } returns Single.just(
            PostsResponse(
                PostsResponseData(
                    "",
                    listOf()
                )
            )
        )
    }
}
