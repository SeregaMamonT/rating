<template>
    <div>
        <player-search-bar :on-select="onSelectPlayer"></player-search-bar>
        <h3>Selected players</h3>
        <b-list-group>
            <b-list-group-item v-for="(player, index) in players">
                <span :href="playerUrl(player)">{{player.surname}} {{player.name}} {{player.patronymic}}</span>
                <a @click="removePlayer(index)">X</a>
            </b-list-group-item>
        </b-list-group>
        <b-button variant="outline-primary" @click="onSaveSquad">Save squad</b-button>
    </div>
</template>
<script>
    import PlayerSearchBar from '../component/player-search-bar'
    import apiService from "../service/apiService";
    import urls from '../urls';

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
            onSelectPlayer(player) {
                this.players.push(player);
            },

            removePlayer(index) {
                this.players.splice(index, 1);
            },

            async onSaveSquad() {
                const res = await apiService.saveSquad(this.players);
            },

            playerUrl(player) {
                return urls.player.findById(player.id);
            },
        },
    }
</script>
<style>
</style>