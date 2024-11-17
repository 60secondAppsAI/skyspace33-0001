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
            <baggage-table
            v-if="baggages && baggages.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:baggages="baggages"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-baggages="getAllBaggages"
             >

            </baggage-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BaggageTable from "@/components/BaggageTable";
import BaggageService from "../services/BaggageService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BaggageTable,
  },
  data() {
    return {
      baggages: [],
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
    async getAllBaggages(sortBy='baggageId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BaggageService.getAllBaggages(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.baggages.length) {
					this.baggages = response.data.baggages;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching baggages:", error);
        }
        
      } catch (error) {
        console.error("Error fetching baggage details:", error);
      }
    },
  },
  mounted() {
    this.getAllBaggages();
  },
  created() {
    this.$root.$on('searchQueryForBaggagesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBaggages();
    })
  }
};
</script>
<style></style>
