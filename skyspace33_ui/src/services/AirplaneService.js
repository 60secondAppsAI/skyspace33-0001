import http from "../http-common"; 

class AirplaneService {
  getAllAirplanes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/airplane/airplanes`, searchDTO);
  }

  get(airplaneId) {
    return this.getRequest(`/airplane/${airplaneId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/airplane?field=${matchData}`, null);
  }

  addAirplane(data) {
    return http.post("/airplane/addAirplane", data);
  }

  update(data) {
  	return http.post("/airplane/updateAirplane", data);
  }
  
  uploadImage(data,airplaneId) {
  	return http.postForm("/airplane/uploadImage/"+airplaneId, data);
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

export default new AirplaneService();
