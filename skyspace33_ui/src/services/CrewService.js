import http from "../http-common"; 

class CrewService {
  getAllCrews(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/crew/crews`, searchDTO);
  }

  get(crewId) {
    return this.getRequest(`/crew/${crewId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/crew?field=${matchData}`, null);
  }

  addCrew(data) {
    return http.post("/crew/addCrew", data);
  }

  update(data) {
  	return http.post("/crew/updateCrew", data);
  }
  
  uploadImage(data,crewId) {
  	return http.postForm("/crew/uploadImage/"+crewId, data);
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

export default new CrewService();
