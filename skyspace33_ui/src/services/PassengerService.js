import http from "../http-common"; 

class PassengerService {
  getAllPassengers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/passenger/passengers`, searchDTO);
  }

  get(passengerId) {
    return this.getRequest(`/passenger/${passengerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/passenger?field=${matchData}`, null);
  }

  addPassenger(data) {
    return http.post("/passenger/addPassenger", data);
  }

  update(data) {
  	return http.post("/passenger/updatePassenger", data);
  }
  
  uploadImage(data,passengerId) {
  	return http.postForm("/passenger/uploadImage/"+passengerId, data);
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

export default new PassengerService();
