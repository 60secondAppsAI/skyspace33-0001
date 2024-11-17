import http from "../http-common"; 

class LoyaltyProgramService {
  getAllLoyaltyPrograms(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/loyaltyProgram/loyaltyPrograms`, searchDTO);
  }

  get(loyaltyProgramId) {
    return this.getRequest(`/loyaltyProgram/${loyaltyProgramId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/loyaltyProgram?field=${matchData}`, null);
  }

  addLoyaltyProgram(data) {
    return http.post("/loyaltyProgram/addLoyaltyProgram", data);
  }

  update(data) {
  	return http.post("/loyaltyProgram/updateLoyaltyProgram", data);
  }
  
  uploadImage(data,loyaltyProgramId) {
  	return http.postForm("/loyaltyProgram/uploadImage/"+loyaltyProgramId, data);
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

export default new LoyaltyProgramService();
