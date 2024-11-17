<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <crew-table
            v-if="crews && crews.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:crews="crews"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-crews="getAllCrews"
             >

            </crew-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CrewTable from "@/components/CrewTable";
import CrewService from "../services/CrewService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CrewTable,
  },
  data() {
    return {
      crews: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllCrews(sortBy='crewId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CrewService.getAllCrews(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.crews.length) {
					this.crews = response.data.crews;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching crews:", error);
        }
        
      } catch (error) {
        console.error("Error fetching crew details:", error);
      }
    },
  },
  mounted() {
    this.getAllCrews();
  },
  created() {
    this.$root.$on('searchQueryForCrewsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCrews();
    })
  }
};
</script>
<style></style>
