import { defineStore } from 'pinia'

export const useTypeStore = defineStore({
    id: 'type',
    state: () => ({
        currentType: null
    }),
    actions: {
        setCurrentType(typeId) {
            this.currentType = typeId
        }
    },
    getters: {
        getCurrentType() {
            return this.currentType
        }
    }
})
