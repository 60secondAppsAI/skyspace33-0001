import http from "../http-common"; 

class AirlineService {
  getAllAirlines(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/airline/airlines`, searchDTO);
  }

  get(airlineId) {
    return this.getRequest(`/airline/${airlineId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/airline?field=${matchData}`, null);
  }

  addAirline(data) {
    return http.post("/airline/addAirline", data);
  }

  update(data) {
  	return http.post("/airline/updateAirline", data);
  }
  
  uploadImage(data,airlineId) {
  	return http.postForm("/airline/uploadImage/"+airlineId, data);
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

export default new AirlineService();
