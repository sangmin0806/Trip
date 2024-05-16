import { localAxios } from '@/util/http-commons';
const local = localAxios();
async function search(input, success, fail) {
  local.post(`trip/list`, input).then(success).catch(fail);
}
async function getLocation(input) {
  const response = await local.post(`trip/location`, input);
  return response.data;
}

export { search, getLocation };
