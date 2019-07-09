<template>
    <div>
        <h1>Hi!</h1>
        <player-search-bar></player-search-bar>
        <b-list-group>
            <b-list-group-item>1</b-list-group-item>
            <b-list-group-item>2</b-list-group-item>
            <b-list-group-item>3</b-list-group-item>
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
            }
        },
    }
</script>

<style>
</style>