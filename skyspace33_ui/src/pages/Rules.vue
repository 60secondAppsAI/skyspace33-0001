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
            <rule-table
            v-if="rules && rules.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:rules="rules"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-rules="getAllRules"
             >

            </rule-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RuleTable from "@/components/RuleTable";
import RuleService from "../services/RuleService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RuleTable,
  },
  data() {
    return {
      rules: [],
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
    async getAllRules(sortBy='ruleId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RuleService.getAllRules(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.rules.length) {
					this.rules = response.data.rules;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching rules:", error);
        }
        
      } catch (error) {
        console.error("Error fetching rule details:", error);
      }
    },
  },
  mounted() {
    this.getAllRules();
  },
  created() {
    this.$root.$on('searchQueryForRulesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRules();
    })
  }
};
</script>
<style></style>
