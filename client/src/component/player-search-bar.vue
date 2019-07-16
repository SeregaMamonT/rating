<template>
    <div>
        <b-form-input v-model="name" placeholder="Enter your name"></b-form-input>
        <b-list-group class="suggested-players">
            <b-list-group-item v-for="player in players">
                <a href="#" @click="onClickPlayer(player)">
                    {{player.surname}} {{player.name}} {{player.patronymic}} ({{player.id}})
                </a>
            </b-list-group-item>
        </b-list-group>
    </div>
</template>

<script>
    import _ from 'lodash';
    import apiService from '../service/apiService'

    export default {
        name: 'PlayerSearchBar',

        props: {
            onSelect: { type: Function, required: true, },
        },

        created() {
            this.debouncedGetPlayers = _.debounce(this.getPlayers, 500);
        },

        data() {
            return {
                players: [],
                name: '',
            }
        },

        methods: {
            async getPlayers(surname, name, patronymic) {
                this.players = await apiService.searchPlayers(surname, name, patronymic);
            },

            onClickPlayer(player) {
                this.clearSelection();
                this.onSelect(player);
            },

            clearSelection() {
                this.name = '';
                this.players = [];
            }
        },

        watch: {
            name(name) {
                name = name.trim();
                if (name) {
                    const tokens = name.split(' ');
                    this.debouncedGetPlayers(tokens[0], tokens[1] || '', tokens[2] || '');
                }
                else {
                    this.clearSelection()
                }
            },
        },
    }
</script>

<style>
    .suggested-players {
        position: absolute;
        z-index: 1000;
    }
</style>