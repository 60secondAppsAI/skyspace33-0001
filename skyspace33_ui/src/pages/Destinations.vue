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
            <destination-table
            v-if="destinations && destinations.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:destinations="destinations"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-destinations="getAllDestinations"
             >

            </destination-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DestinationTable from "@/components/DestinationTable";
import DestinationService from "../services/DestinationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DestinationTable,
  },
  data() {
    return {
      destinations: [],
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
    async getAllDestinations(sortBy='destinationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DestinationService.getAllDestinations(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.destinations.length) {
					this.destinations = response.data.destinations;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching destinations:", error);
        }
        
      } catch (error) {
        console.error("Error fetching destination details:", error);
      }
    },
  },
  mounted() {
    this.getAllDestinations();
  },
  created() {
    this.$root.$on('searchQueryForDestinationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDestinations();
    })
  }
};
</script>
<style></style>
