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
            <pilot-table
            v-if="pilots && pilots.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:pilots="pilots"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-pilots="getAllPilots"
             >

            </pilot-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PilotTable from "@/components/PilotTable";
import PilotService from "../services/PilotService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PilotTable,
  },
  data() {
    return {
      pilots: [],
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
    async getAllPilots(sortBy='pilotId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PilotService.getAllPilots(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.pilots.length) {
					this.pilots = response.data.pilots;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching pilots:", error);
        }
        
      } catch (error) {
        console.error("Error fetching pilot details:", error);
      }
    },
  },
  mounted() {
    this.getAllPilots();
  },
  created() {
    this.$root.$on('searchQueryForPilotsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPilots();
    })
  }
};
</script>
<style></style>
