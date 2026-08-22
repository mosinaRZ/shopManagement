package ir.hamedan.shopmanagement.feature.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hamedan.shopmanagement.core.ui.state.UiState
import ir.hamedan.shopmanagement.domain.model.Product
import ir.hamedan.shopmanagement.domain.usecase.product.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    val productsState: StateFlow<UiState<List<Product>>> = combine(
        getProductsUseCase(),
        _searchQuery,
        _selectedCategory
    ) { products, query, category ->
        val filtered = products.filter { product ->
            val matchesQuery = product.name.contains(query, ignoreCase = true)
            val matchesCategory = category == null || product.category == category
            matchesQuery && matchesCategory
        }
        UiState.Success(filtered)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String?) {
        _selectedCategory.value = category
    }

    fun saveProduct(
        id: Long = 0L,
        name: String,
        category: String?,
        quantity: Int,
        purchasePrice: Double,
        sellPrice: Double,
        minQuantityAlert: Int = 5,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val product = Product(
                    id = id,
                    name = name.trim(),
                    category = category?.trim(),
                    quantity = quantity,
                    purchasePrice = purchasePrice,
                    sellPrice = sellPrice,
                    minQuantityAlert = minQuantityAlert,
                    createdAt = System.currentTimeMillis()
                )
                if (id == 0L) {
                    addProductUseCase(product)
                } else {
                    updateProductUseCase(product)
                }
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "خطا در ذخیره کالا")
            }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            deleteProductUseCase(product)
        }
    }
}