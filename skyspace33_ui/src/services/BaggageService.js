import http from "../http-common"; 

class BaggageService {
  getAllBaggages(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/baggage/baggages`, searchDTO);
  }

  get(baggageId) {
    return this.getRequest(`/baggage/${baggageId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/baggage?field=${matchData}`, null);
  }

  addBaggage(data) {
    return http.post("/baggage/addBaggage", data);
  }

  update(data) {
  	return http.post("/baggage/updateBaggage", data);
  }
  
  uploadImage(data,baggageId) {
  	return http.postForm("/baggage/uploadImage/"+baggageId, data);
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

export default new BaggageService();
