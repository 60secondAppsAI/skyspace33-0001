import http from "../http-common"; 

class DestinationService {
  getAllDestinations(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/destination/destinations`, searchDTO);
  }

  get(destinationId) {
    return this.getRequest(`/destination/${destinationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/destination?field=${matchData}`, null);
  }

  addDestination(data) {
    return http.post("/destination/addDestination", data);
  }

  update(data) {
  	return http.post("/destination/updateDestination", data);
  }
  
  uploadImage(data,destinationId) {
  	return http.postForm("/destination/uploadImage/"+destinationId, data);
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

export default new DestinationService();
