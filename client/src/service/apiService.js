const ServerUrl = `http://localhost:8080`;
const ApiUrl = `${ServerUrl}/api`;

class ApiService {
    async searchPlayers(surname, name, patronymic) {
        const url = `${ApiUrl}/player/search?surname=${surname}&name=${name}&patronymic=${patronymic}`;
        return (await fetch(url)).json();
    }

    async saveSquad(players) {
        return await fetch(`${ApiUrl}/squad/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(players),
        });
    }
}

export default new ApiService();
