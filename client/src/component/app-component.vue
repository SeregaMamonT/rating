<template>
    <div>
        <h1>Hi!</h1>
        <player-search-bar :on-select="onSelectPlayer"></player-search-bar>
        <h3>Selected players</h3>
        <b-list-group>
            <b-list-group-item v-for="(player, index) in players">
                {{player.surname}} {{player.name}} {{player.patronymic}}
                <span @click="removePlayer(index)">X</span>
            </b-list-group-item>
        </b-list-group>
    </div>
</template>

<script>
    import PlayerSearchBar from '../player-search-bar'

    export default {
        name: 'AppComponent',

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
        },
    }
</script>

<style>
</style>