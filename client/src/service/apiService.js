import urls from '../urls';

class ApiService {
    async searchPlayers(surname, name, patronymic) {
        const url = urls.player.search(surname, name, patronymic);
        return (await fetch(url)).json();
    }

    async saveSquad(players) {
        return await fetch(urls.squad.create, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(players),
        });
    }
}

export default new ApiService();
