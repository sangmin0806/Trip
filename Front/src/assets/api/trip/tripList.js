import { localAxios } from '@/util/http-commons';
const local = localAxios();
async function putTripList(param, success, fail) {
    local.post(`trip/tripList`, param).then(success).catch(fail);
}

export { putTripList };
