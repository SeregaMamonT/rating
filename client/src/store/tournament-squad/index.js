import Vue from 'vue';
import {ADD_TO_SQUAD, DELETE_FROM_SQUAD} from "./mutations";

export default {
    namespaced: true,

    state: {
        squad: [],
    },

    getters: {
        squad: state => state.squad,
    },

    mutations: {
        [ADD_TO_SQUAD](state, player) {
            state.squad.push(player);
        },

        [DELETE_FROM_SQUAD](state, index) {
            Vue.delete(state.squad, index);
        },
    },

    actions: {},
};
