import axios from 'axios';
import urls from '../urls';

class ApiService {
    async searchPlayers(surname, name, patronymic) {
        const url = urls.player.search(surname, name, patronymic);
        return (await axios.get(url)).data;
    }

    async saveSquad(players) {
        return await axios.post(urls.squad.create, players);
    }
}

export default new ApiService();
