import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

import tournamentSquad from './tournament-squad';

const store = new Vuex.Store({
    modules: {
        tournamentSquad,
    },

    state: {
    },

    mutations: {
    },

    actions: {
    },
});

export default store;