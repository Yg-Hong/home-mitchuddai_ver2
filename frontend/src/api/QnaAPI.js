import { localAxios } from "@/utils/http-commons";

const QnaAPI = localAxios();

const getQnaList = (pageInfo, success, fail) => {
  QnaAPI.get(`/posts?page=${pageInfo.page}&size=${pageInfo.size}`).then(success).catch(fail);
};

const getQnaDetail = (id, success, fail) => {
  QnaAPI.get(`/posts/${id}`).then(success).catch(fail);
};

const writeQna = (qna, success, fail) => {
  QnaAPI.post("/posts", qna).then(success).catch(fail);
};

const updateQna = (id, qna, success, fail) => {
  QnaAPI.patch(`/posts/${id}`, qna).then(success).catch(fail);
};

const deleteQna = (id, success, fail) => {
  QnaAPI.delete(`/posts/${id}`).then(success).catch(fail);
};

export default { getQnaList, getQnaDetail, writeQna, updateQna, deleteQna };
