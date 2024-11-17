import http from "../http-common"; 

class PilotService {
  getAllPilots(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/pilot/pilots`, searchDTO);
  }

  get(pilotId) {
    return this.getRequest(`/pilot/${pilotId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/pilot?field=${matchData}`, null);
  }

  addPilot(data) {
    return http.post("/pilot/addPilot", data);
  }

  update(data) {
  	return http.post("/pilot/updatePilot", data);
  }
  
  uploadImage(data,pilotId) {
  	return http.postForm("/pilot/uploadImage/"+pilotId, data);
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

export default new PilotService();
