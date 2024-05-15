import { localAxios } from '@/util/http-commons';
const local = localAxios();
async function search(input, success, fail) {
    local.post(`trip/list`, input).then(success).catch(fail);
}
export { search };
