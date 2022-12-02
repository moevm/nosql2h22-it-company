import React, {useEffect, useState} from "react";
import Stack from "@mui/material/Stack";
import {PersonAdvancedSearch} from "./PersonAdvancedSearch";
import {PersonInfo} from "./PersonInfo";
import {PersonSearch} from "./PersonSearch";
import {personRequest} from "../utils/HTTPRequest";
import {PERSON_PAGE_TITLE} from "../constants";

export function PersonModule() {
    const [personInfo, setPersonInfo] = useState<IPerson | null>(null);
    const [advancedSearch, setAdvancedSearch] = useState<boolean>(false);

    useEffect(() => {
        document.title = PERSON_PAGE_TITLE;
        personRequest.get<IPerson>(
            `${process.env.REACT_APP_PERSON_GET}`
        ).then(response => {
            setPersonInfo(response.data);
        }).catch((error: IError) => {
            console.log(error);
        });
    }, []);

    return (
        <>
            {advancedSearch ? <PersonAdvancedSearch setPerson={setPersonInfo} setAdvancedSearch={setAdvancedSearch} /> : <Stack>
                <PersonSearch setPerson={setPersonInfo} setAdvancedSearch={setAdvancedSearch} />
                <PersonInfo person={personInfo} />
            </Stack>}
        </>
    );
}