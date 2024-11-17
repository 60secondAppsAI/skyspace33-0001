import http from "../http-common"; 

class RuleService {
  getAllRules(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/rule/rules`, searchDTO);
  }

  get(ruleId) {
    return this.getRequest(`/rule/${ruleId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/rule?field=${matchData}`, null);
  }

  addRule(data) {
    return http.post("/rule/addRule", data);
  }

  update(data) {
  	return http.post("/rule/updateRule", data);
  }
  
  uploadImage(data,ruleId) {
  	return http.postForm("/rule/uploadImage/"+ruleId, data);
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

export default new RuleService();
