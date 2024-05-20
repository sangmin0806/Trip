import { localAxios } from '@/util/http-commons';
const local = localAxios();
async function search(input, success, fail) {
    local.get(`trip/list`, { params: input }).then(success).catch(fail);
}
async function getLocation(input) {
    const response = await local.get(`trip/location`, { params: input });
    return response.data;
}

export { search, getLocation };
