const ServerUrl = `http://localhost:8080`;
const ApiUrl = `${ServerUrl}/api`;

const urls = {
    get player() {
        const url = `${ApiUrl}/player`;
        return {
            search: (surname, name, patronymic) => `${url}/search?${formatParams({ surname, name, patronymic })}`,
            findById: (playerId) => `${url}/${playerId}`,
        };
    },

    get squad() {
        const url = `${ApiUrl}/squad`;
        return {
            create: `${url}/create`,
        };
    },
};

function formatParams(params) {
    return Object.entries(params)
        .map(([key, value]) => `${key}=${value || ''}`)
        .join('&');
}

export default urls;