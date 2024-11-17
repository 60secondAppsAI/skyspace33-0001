import http from "../http-common"; 

class FlightService {
  getAllFlights(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flight/flights`, searchDTO);
  }

  get(flightId) {
    return this.getRequest(`/flight/${flightId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flight?field=${matchData}`, null);
  }

  addFlight(data) {
    return http.post("/flight/addFlight", data);
  }

  update(data) {
  	return http.post("/flight/updateFlight", data);
  }
  
  uploadImage(data,flightId) {
  	return http.postForm("/flight/uploadImage/"+flightId, data);
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

export default new FlightService();
