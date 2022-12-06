import React, {useState} from "react";
import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import {styled} from "@mui/material/styles";
import Typography from "@mui/material/Typography";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import MenuItem from "@mui/material/MenuItem";
import {documentRequest} from "../utils/HTTPRequest";
import {DOCUMENT_TYPES} from "../constants";

interface IProps {
    onDeliver: (document: IDocument) => void
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px"
}));

export function DocumentDeliver({onDeliver}: IProps) {
    const [document, setDocument] = useState<string>("");

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
    }

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDocument(event.target.value);
    }

    const handleDeliver = () => {
        console.log(typeof documentRequest);
        documentRequest.post<IDocument>(`${process.env.REACT_APP_DOCUMENT_GET}`, {
            type: document
        }).then(response => {
            onDeliver(response.data);
        });
    }

    return (
        <Item elevation={6}>
            <Container>
                <Stack direction="row" spacing={2}>
                    <Typography variant="body1" component="span">
                        Справка:
                    </Typography>
                    <form onSubmit={handleSubmit}>
                        <FormControl fullWidth>
                            <TextField
                                name="document"
                                type="text"
                                required
                                select
                                onChange={handleInput}
                                variant="outlined"
                            >
                                {DOCUMENT_TYPES.map((option) => (
                                    <MenuItem key={option.name} value={option.name}>
                                        {option.value}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </FormControl>
                    </form>
                </Stack>
                <Button
                    onClick={handleDeliver}
                >
                    Заказать
                </Button>
            </Container>
        </Item>
    );
}