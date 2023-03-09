import { reactive } from 'vue'

const state = reactive({
    products: [],
    currentType: null
})

const getters = {
    products: (state) => state.products,
    currentType: (state) => state.currentType,
    filteredProducts: (state) => {
        if (state.currentType) {
            return state.products.filter((product) => product.type === state.currentType)
        } else {
            return state.products
        }
    },
    getProducts: (state) => {
        return state.products
    }
}

const mutations = {
    setProducts: (state, products) => {
        state.products = products
    },
    setCurrentType: (state, type) => {
        state.currentType = type
    }
}

export default {
    state,
    getters,
    mutations
}
