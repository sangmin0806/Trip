import { localAxios } from '@/util/http-commons';
const local = localAxios();

// 유저 상세 정보 가져오기
async function fetchUserDetail(userId) {
  try {
    const response = await local.get(`/user/detail/${userId}`);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}

// 유저 정보 수정하기
async function updateUserDetail(user) {
  try {
    const response = await local.put(`/user/${user.userId}`, user);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
}

export { fetchUserDetail, updateUserDetail };
