<template>
    <div>
        <player-search-bar :on-select="onSelectPlayer"></player-search-bar>
        <h3>Selected players</h3>
        <b-list-group>
            <b-list-group-item v-for="(player, index) in squad">
                <span :href="playerUrl(player)">{{player.surname}} {{player.name}} {{player.patronymic}}</span>
                <a href="#" @click="removePlayer(index)">X</a>
            </b-list-group-item>
        </b-list-group>
        <b-button variant="outline-primary" @click="onSaveSquad">Save squad</b-button>
    </div>
</template>
<script>
    import {mapGetters, mapMutations} from 'vuex';

    import PlayerSearchBar from '../component/player-search-bar'
    import apiService from "../service/apiService";
    import urls from '../urls';
    import {ADD_TO_SQUAD, DELETE_FROM_SQUAD} from '../store/tournament-squad/mutations';

    export default {
        name: 'MainPage',

        components: {
            'player-search-bar': PlayerSearchBar,
        },

        data() {
            return {
                players: [],
            }
        },

        methods: {
            ...mapMutations('tournamentSquad', {
                addPlayerToSquad: ADD_TO_SQUAD,
                deletePlayerFromSquad: DELETE_FROM_SQUAD,
            }),

            onSelectPlayer(player) {
                this.addPlayerToSquad(player);
            },

            removePlayer(index) {
                this.deletePlayerFromSquad(index);
            },

            async onSaveSquad() {
                const res = await apiService.saveSquad(this.players);
            },

            playerUrl(player) {
                return urls.player.findById(player.id);
            },
        },

        computed: {
            ...mapGetters('tournamentSquad', {
                squad: 'squad',
            }),
        },
    }
</script>
<style>
</style>