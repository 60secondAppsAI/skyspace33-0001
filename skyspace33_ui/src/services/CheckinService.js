import http from "../http-common"; 

class CheckinService {
  getAllCheckins(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/checkin/checkins`, searchDTO);
  }

  get(checkinId) {
    return this.getRequest(`/checkin/${checkinId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/checkin?field=${matchData}`, null);
  }

  addCheckin(data) {
    return http.post("/checkin/addCheckin", data);
  }

  update(data) {
  	return http.post("/checkin/updateCheckin", data);
  }
  
  uploadImage(data,checkinId) {
  	return http.postForm("/checkin/uploadImage/"+checkinId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CheckinService();
