package com.mazaady.example


import com.google.common.truth.Truth.assertThat
import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.presentation.fragments.main.MainViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.mazaady.task.utils.Resource
import com.mazaady.task.utils.Status
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.apache.tools.ant.util.XMLFragment.Child
import org.junit.Rule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val mainRepo = mockk<MainRepo>()

    private lateinit var viewModel: MainViewModel
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp() {

        viewModel = MainViewModel(mainRepo)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }


    @Test
    fun `test getMainCategories success`() = testScope.runBlockingTest {

        /*
        // Given
        val categories = listOf(CatData(null, null,"","","",null), (CatData(null, null,"","","",null)))
            val sd = MainResponse(categories)


        coEvery { mainRepo.getMainCategories() } returns flow { emit(response) }

        // When
        viewModel.getMainCategories()

        // Then
        val result = viewModel.getMainCategories.first()
        assert(result is Resource.Success)
        assert((result as Resource.Success).data == response)

        val category =      Category(null, "Category 1","first desc",0,0,"img","first","first"),

        val properties = emptyList<PropData>()

        val response = MainResponse(1,properties,"",)
        coEvery { mainRepo.getOptionChild(option.id.toString()) } returns flow { emit(response) }

        // When
        viewModel.getOptionChild(option)

        // Then
        val result = viewModel.getOptionsChild.first()
        assertThat(result.status ).isEqualTo(Status.SUCCESS)
        assertThat(result.data).isEqualTo(response.data)

         */


    }

    @Test
    fun `test getMainCategories empty success`() = testScope.runBlockingTest {

        /*
        // Given
        val categories = emptyList<CatData>()
        val response = MainResponse(categories)
        coEvery { mainRepo.getMainCategories() } returns flow { emit(response) }

        // When
        viewModel.getMainCategories()

        // Then
        val result = viewModel.getMainCategories.first()
        assert(result is )
        assert((result as Resource.Error).message == "Categories Is Empty")

         */
    }

    @Test
    fun `test getProperties success`() = testScope.runBlockingTest {

        /*
        // Given
        val categoryId = "1"
        val properties = listOf(PropData("1", "Property 1"), PropData("2", "Property 2"))
        val response = MainResponse(properties)
        coEvery { mainRepo.getProperties(categoryId) } returns flow { emit(response) }

        // When
        viewModel.getProperties(categoryId)

        // Then
        val result = viewModel.getProperties.first()
        assert(result is Resource.Success)
        assert((result as Resource.Success).data == response)

         */
    }

    @Test
    fun `test getProperties empty success`() = testScope.runBlockingTest {

        /*
        // Given
        val categoryId = "1"
        val properties = emptyList<PropData>()
        val response = MainResponse(properties)
        coEvery { mainRepo.getProperties(categoryId) } returns flow { emit(response) }

        // When
        viewModel.getProperties(categoryId)

        // Then
        val result = viewModel.getProperties.first()
        assert(result is Resource.Error)
        assert((result as Resource.Error).message == "Properties Is Empty")

         */
    }

    @Test
    fun `test getOptionChild success`() = testScope.runBlockingTest {

        /*
        // Given
        val option = Option("1", "Option 1")
        val properties = listOf(PropData("1", "Property 1"), PropData("2", "Property 2"))
        val response = MainResponse(properties)
        coEvery { mainRepo.getOptionChild(option.id.toString()) } returns flow { emit(response) }

        // When
        viewModel.getOptionChild(option)

        // Then
        val result = viewModel.getOptionsChild.first()
        assert(result is Resource.Success)
        assert((result as Resource.Success).data == response)
        */
    }

    @Test
    fun `test getOptionChild empty success`() = testScope.runBlockingTest  {

         // Given
        var options= mutableListOf<Option>()
        options.add(Option(true, 1,"hi",9,"ok"))
        options.add(Option(true, 2,"hello",10,"hello"))
        val properties = emptyList<PropData>()

        properties[0].options= options

        val response = MainResponse(1,properties,"",)
        coEvery { mainRepo.getOptionChild(properties[0].id.toString()) } returns flow { emit(response) }

        // When
        viewModel.getOptionChild(options[0])

        // Then
        val result = viewModel.getOptionsChild.first()
        assertThat(result.status ).isEqualTo(Status.SUCCESS)
        assertThat(result.data).isEqualTo(response.data)
    }

    @Test
    fun `test loadCategories with success`() = runBlocking {
        viewModel = MainViewModel(mainRepo)

        var list= mutableListOf<Category>()
        // ...
        val mockCategories = listOf(
            Category(null, "Category 1","first desc",0,0,"img","first","first"),
            Category(null, "Category 2","first desc",1,1,"img","first","first"),
            Category(null, "Category 3","first desc",2,2,"img","first","first"),
        )
        val categories= mainRepo.getMainCategories().collect{

            list= it.data.categories as MutableList<Category>
        }
        whenever(list).thenReturn(mockCategories as MutableList<Category>?)

        // Set up mock repository response

        // Trigger loadCategories
        viewModel.getMainCategories()
        val result= viewModel.getMainCategories.first()


        assertThat(result.data?.data?.categories?.size).isEqualTo(2)

        assertThat(result.data?.data?.categories?.get(0)?.slug).isEqualTo("first")
        // Verify viewModel state


    }
}