const ApiUrl = `http://localhost:8080/api`;

class ApiService {
    async searchPlayers(surname, name, patronymic) {
        const url = `${ApiUrl}/player?surname=${surname}&name=${name}&patronymic=${patronymic}`;
        return (await fetch(url)).json();
    }
}

export default new ApiService();
