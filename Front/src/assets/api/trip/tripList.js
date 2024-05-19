import { localAxios } from '@/util/http-commons';
const local = localAxios();
async function registerPlan(param, success, fail) {
    local.post(`trip/planLists`, param).then(success).catch(fail);
}
async function getPlanLists(success, fail) {
    local.get(`trip/planLists`).then(success).catch(fail);
}
async function getPlan(id, success, fail) {
    const url = `trip/planLists/${id}`;
    local.get(url).then(success).catch(fail);
}
async function deletePlan(id, success, fail) {
    const url = `trip/planLists/${id}`;
    local.post(url).then(success).catch(fail);
}
export { registerPlan, getPlanLists, getPlan, deletePlan };
